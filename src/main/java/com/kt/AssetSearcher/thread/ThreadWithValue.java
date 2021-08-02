package com.kt.AssetSearcher.thread;

import java.util.function.Supplier;

public class ThreadWithValue<T> extends Thread {
	private T value;
	
	public T getValue() {
		try {
			this.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		return this.value;
	}
	
	private Supplier<T> supplier;
	
	public ThreadWithValue(Supplier<T> supplier) {
		this.supplier = supplier;
		this.start();
	}
	
	@Override
	public void run() {
		this.value = this.supplier.get();
	}
}
