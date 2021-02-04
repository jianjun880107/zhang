package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.kuangke.asyn.MyAsyn;

public class AsynListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {


	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		MyAsyn myAsyn = new MyAsyn();
		myAsyn.start();

	}

}
