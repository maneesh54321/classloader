package com.ms.ioc.container;

import com.ms.ioc.IMeeting;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOCException {
		Container container = new Container();
		container.init();

		IMeeting meeting = container.resolve(IMeeting.class);
		meeting.init();

		System.out.println(Arrays.toString(meeting.getAttendees()));
	}
}
