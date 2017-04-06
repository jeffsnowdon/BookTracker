package com.jeff.booktracker.db;

import java.util.List;
import java.util.function.Supplier;

import com.jeff.booktracker.lifecycle.Command;
import com.jeff.booktracker.lifecycle.Initializable;

public class DBInitializer implements Initializable {

	private Supplier<List<DBCommand>> dbBeansSupplier;

	public DBInitializer(Supplier<List<DBCommand>> dbBeans) {
		this.dbBeansSupplier = dbBeans;
	}

	@Override
	public void init() {
		dbBeansSupplier.get().stream().forEach(Command::execute);
	}

}
