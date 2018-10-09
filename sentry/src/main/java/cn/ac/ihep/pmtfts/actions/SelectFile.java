package cn.ac.ihep.pmtfts.actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gov.lbl.nest.sentry.Action;
import gov.lbl.nest.sentry.Context;

public class SelectFile extends SimpleFileVisitor<Path> {

	private List<SyphonableFile> files = new ArrayList<SyphonableFile>();
	private Date after;
	private Date before;
	public SelectFile(final List<SyphonableFile> files, final Date after, final Date before) {
		this.files = files;
		this.after=after;
		this.before=before;
	}
		
	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		File file = path.toFile();
		Date modified = new Date(file.lastModified());
		files.add(new SyphonableFile(path.toString(),modified));
		if (modified.before(before) && !modified.before(after))
		{
			files.add(new SyphonableFile(path.toString(),modified));
		}
		return FileVisitResult.CONTINUE;
	}
	//找到目录下的文件
	
}
