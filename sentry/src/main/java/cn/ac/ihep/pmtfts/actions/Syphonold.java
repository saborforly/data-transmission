package cn.ac.ihep.pmtfts.actions;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.FolderNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.lbl.nest.common.configure.SuspendableImpl;
import gov.lbl.nest.jee.watching.LastWatched;
import gov.lbl.nest.sentry.Action;
import gov.lbl.nest.sentry.ExecutionException;
import gov.lbl.nest.sentry.RealizedAction;
import gov.lbl.nest.jee.watching.LastWatched;
import gov.lbl.nest.jee.watching.Watcher;
import gov.lbl.nest.sentry.Context;

public class Syphonold extends SuspendableImpl implements RealizedAction {
	/**
	 * The {@link Logger} used by this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(Syphonold.class);
	private static final String XML_FORMAT_PROPERTY = "jaxb.formatted.output";
	private final String role;
	private final Watcher watcher;
	private final int cushion;
	private static final int DEFAULT_DEPTH = 300;
	private static final int DEFAULT_CUSHION = 5;// 24hours
	private static String USER_HOME_STRING = "~";
	private static final String USER_HOME_PROPERTY = "user.home";
	private int selectionDepth;
	private File baseDir;
	private File dropboxDir;

	public Syphonold(final Action action, final Boolean suspend, final Context context) {
		super(suspend);
		// TODO Auto-generated constructor stub
		role = action.getParameter("role");
		if (null == role) {
			throw new IllegalStateException("\"role\" must be specified in action declaration");
		}
		final String inputCushion = action.getParameter("cushion");
		if (null == inputCushion) {
			cushion = DEFAULT_CUSHION;
		} else {
			int cushionToUse;
			try {
				cushionToUse = Integer.parseInt(inputCushion);
			} catch (NumberFormatException e) {
				cushionToUse = DEFAULT_CUSHION;
			}
			cushion = cushionToUse;
		}
		final String depth = action.getParameter("selectionDepth");
		if (null == depth) {
			selectionDepth = DEFAULT_DEPTH;
		} else {
			int depthToUse;
			try {
				depthToUse = Integer.parseInt(depth);
			} catch (NumberFormatException e) {
				depthToUse = DEFAULT_DEPTH;
			}
			selectionDepth = depthToUse;
		}

		watcher = context.getInstance(Watcher.class);
		final String base = action.getParameter("base");
		if (null == base) {
			throw new IllegalStateException("\"base\" must be specified in action declaration");
		}
		if (base.startsWith(USER_HOME_STRING + File.separator)) {
			final String userHome = System.getProperty(USER_HOME_PROPERTY);
			baseDir = new File(userHome + base.substring(USER_HOME_STRING.length()));
		} else {
			baseDir = new File(base);
		}

		final String dropbox = action.getParameter("dropbox");
		if (null == dropbox) {
			throw new IllegalStateException("\"dropbox\" must be specified in action declaration");
		}
		if (dropbox.startsWith(USER_HOME_STRING + File.separator)) {
			final String userHome = System.getProperty(USER_HOME_PROPERTY);
			dropboxDir = new File(userHome + dropbox.substring(USER_HOME_STRING.length()));
		} else {
			dropboxDir = new File(dropbox);
		}
	}

	@Override

	public void execute() throws ExecutionException {
		LOG.info("PMT-TEST data is Syphoning!");

		final LastWatched last = watcher.getLastWatched(role);

		if (null == last) {
			LOG.debug("\"" + role + "\" is not watching, so will not do anything");
		} else {
			final Date lastDateTime = last.getLastDateTime();
			LOG.debug("Looking for new entries for \"" + role + "\" on or after " + lastDateTime);
			final List<SyphonableFile> foundFiles = watcher.forwardLastWatched(last, getNewFiles(lastDateTime, cushion),
					selectionDepth);
			for (SyphonableFile foundFile : foundFiles) {
				File semphoreFile = new File(dropboxDir,new File(foundFile.getItem()).getName() + ".sem");
				JAXBContext content;
				
				try {
					content = JAXBContext.newInstance(Semphore.class);
					Marshaller marshaller = content.createMarshaller();
					marshaller.setProperty(XML_FORMAT_PROPERTY, Boolean.TRUE);
					marshaller.marshal(new Semphore(foundFile.getItem()), semphoreFile);
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}

	}

	List<SyphonableFile> getNewFiles(Date lastDateTime, int cushion) {

		List<SyphonableFile> result = new ArrayList<SyphonableFile>();
		Date after = lastDateTime;
		Date before = new Date(new Date().getTime() - TimeUnit.MILLISECONDS.convert(cushion, TimeUnit.MINUTES));
		SelectFile selectFile = new SelectFile(result, after, before);
		try {
			Files.walkFileTree(baseDir.toPath(), selectFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(result, new Comparator<SyphonableFile>() {
			@Override
			public int compare(SyphonableFile o1, SyphonableFile o2) {
				final Date d1 = o1.getWhenItemChanged();
				final long t1 = d1.getTime();
				final Date d2 = o2.getWhenItemChanged();
				final long t2 = d2.getTime();
				if (t1 > t2) {
					return 1;
				} else if (t1 < t2) {
					return -1;
				}
				return 0;
			}
		});
		return result;
	}

}
