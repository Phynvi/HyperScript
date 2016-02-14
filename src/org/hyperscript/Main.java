package org.hyperscript;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;

import com.sun.tools.attach.VirtualMachine;

import org.hyperscript.messages.MessageEvent;
import org.hyperscript.messages.MessageHandler;
import org.hyperscript.messages.MessageListener;

public class Main {
	
	public static volatile ClassLoader CLASSLOADER;

	public static void main(String[] args) throws Exception {
		
		/*MessageHandler.addMessageListener(new MessageListener() {

			@Override
			public void onMessage(MessageEvent e) {
				System.out.println(e.getMessage());
			}

			@Override
			public boolean filter(MessageEvent e) {
				return false;
			}
			
		});*/
		downloadClientJar();
		startInstrumentation();
		startClient();
	}
	
	private static void downloadClientJar() {
		try {
			// Add the conf dir to the classpath
			// Chain the current thread classloader
			URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("http://hyperion-rsps.co.uk/Hyperion.jar")});

			// Replace the thread classloader - assumes
			// you have permissions to do so
			CLASSLOADER = urlClassLoader;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void startInstrumentation() {
        String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();
        int p = nameOfRunningVM.indexOf('@');
        String pid = nameOfRunningVM.substring(0, p);

        try {
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(getCurrentJarPath(), "");
            vm.detach();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	private static String getCurrentJarPath() {
		String path = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getPath();
		String decodedPath;
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
			System.out.println(decodedPath);
			return decodedPath;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private static void startClient() {
		try {
			Main.CLASSLOADER.loadClass("Loader").getDeclaredMethod("main", new Class<?>[]{String[].class}).invoke(null, new Object[]{ new String[0] });
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
