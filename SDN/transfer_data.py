class WatchDogThread(BaseThread):
    """
    monitor the worker registry to manage worker
    """

    def __init__(self, master):
        name = '.'.join(['DistJET.BaseThread','WatchDog'])
        print("[BaseThread]: create new thread : %s" % name)
        self.setDaemon(1)
        self.lock = threading.RLock()
        self.__should_stop = False
        base_thread_log.debug('BaseThread object created:%s', self.__class__.__name__)
        
        self.master = master
        self.processing = False

    def run(self):
        control_log.info('Control Thread start...')
        while not self.get_stop_flag():
            start_time=datetime.datetime.today()
            
            # check if lost
            lostworker = self.master.worker_registry.checkLostWorker()
            if lostworker:
                control_log.warning('Lost worker = %s' % lostworker)
                for wid in lostworker:
                    #TODO: maybe use other method to deal with the lost worker
                    #self.master.remove_worker(wid)
                    self.master.worker_registry.setStatus(wid,WorkerStatus.LOST)
                    self.master.lost_worker(wid)

            # check if all worker idle
            if self.master.worker_registry.checkIdle():
                # finalize all worker
                self.master.finalize_worker()

            # check idle timeout worker
            idleworker = self.master.worker_registry.checkIDLETimeout()
            if idleworker:
                control_log.warning('Find Idle timeout worker %s'%idleworker)
                # TODO: do something to reduce the resource

            #check error status worker
            errworker = self.master.worker_registry.checkError()
            if errworker:
                control_log.warning('Find error status worker %s'%errworker) 
            #for wid in errworker:
                #self.master.remove_worker(wid)

            # print worker status
            master_log.info('[Master] Worker status = %s'%self.master.worker_registry.get_worker_status())

            # save worker status to file
            rundir = self.master.cfg.getCFGattr('rundir')
            with open(os.environ['HOME']+'/.DistJET/worker','w+') as workerfile:
                workerfile.truncate()
                workerfile.write('wid\tstatus\trunning\tlasttime\n')
                for wid in self.master.worker_registry:
                    entry = self.master.worker_registry.get_entry(wid)
                    if entry is None:
                        continue
                    w_d = entry.toDict()
                    workerfile.write(str(w_d['wid'])+'\t'+str(WorkerStatus.desc(w_d['status']))+'\t'+str(w_d['running_task'])+'\t'+str(w_d['last_connect']+'\n'))
                workerfile.flush()


            if not lostworker and not idleworker:
                control_log.debug('No lost worker and idle worker')
            if self.master.cfg.getPolicyattr('control_delay'):
                time.sleep(self.master.cfg.getPolicyattr('control_delay'))
            else:
                time.sleep(0.1)

        
    def activateProcessing(self):
        self.processing = True
