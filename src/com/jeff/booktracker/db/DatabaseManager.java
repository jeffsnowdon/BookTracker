package com.jeff.booktracker.db;

import java.util.logging.Logger;

import com.jeff.booktracker.lifecycle.Disposable;
import com.jeff.booktracker.lifecycle.Initializable;

public class DatabaseManager implements Initializable, Disposable {

	private static final String DATABASE_PATH = "C:\\Code\\MediaHistory\\workspace\\MediaCollection\\mysql\\bin\\";
	private static final String DATABASE_START_CMD = "mysqld.exe";
	private static final String DATABASE_SHUTDOWN_CMD = "taskkill";
	private Process databaseProcess;
	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

	@Override
	public void init() {
		logger.severe("Test");
		ProcessBuilder pb = new ProcessBuilder(DATABASE_PATH + DATABASE_START_CMD);
		try {
			databaseProcess = pb.start();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void dispose() {
		if (databaseProcess != null){
			// database process was created my this application
			databaseProcess.destroy();
		}else{
			// try to kill a potential dangling database process
			ProcessBuilder pb = new ProcessBuilder(DATABASE_SHUTDOWN_CMD, "/im", DATABASE_START_CMD, "/f");
			try {
				pb.start();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		
	}

}
