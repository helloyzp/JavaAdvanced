package com.example.javaadvanced.io.classTop;

import android.os.TestLooperManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ObjectStreamTest {
	private static File newFile(String path){
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	private static void writeObject(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(Constants.DATA_PATH+ "object.txt"));
			for(int i = 0; i < 10; i++){
				oos.writeObject(new Person("欧阳锋[" + i +"]", i));
			}
			oos.writeObject(null);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void readObject() {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(newFile(Constants.DATA_PATH+ "object.txt"))));
			while (ois.available() != -1) {
				try {
					Object object = ois.readObject();
					Person person = (Person) object;
					System.out.println(person.toString());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readObject2() {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(newFile(Constants.DATA_PATH+ "object.txt"))));
			while (ois.available() != -1) {
				try {
					Object object = ois.readObject();
					//读取数据时，如果是完全相同的类，但在不同的包下，也是不行的。
					com.example.javaadvanced.io.test.Person person = (com.example.javaadvanced.io.test.Person) object;
					System.out.println(person.toString());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeObjectByArray(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream( 
					new FileOutputStream(newFile("src/testtxt/objectArrays.txt")));
			Person[] persons = new Person[10];
			for(int i = 0; i < 10; i++){
				Person person = new Person("洪七公[" + i + "]", i);
				persons[i] = person;
			}
			oos.writeObject(persons);
			oos.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 通过集合写入对象
	 */
	private static void writeObjectByList() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("src/testtxt/objectByList.txt"));
			List<Person> persons=new ArrayList<Person>();
			for (int i = 1; i < 10; i++) {
				Person person = new Person("欧阳鹏 List[" + (20+i)+"]", 20+i);
				persons.add(person);
			}
			//写入List
			oos.writeObject(persons);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		writeObject();
//		writeObjectByArray();
//		writeObjectByList();


		writeObject();
		readObject();
	}

}
