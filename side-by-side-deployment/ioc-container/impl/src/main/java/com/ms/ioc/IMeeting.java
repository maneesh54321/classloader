package com.ms.ioc;

public interface IMeeting {
	void setAttendees(String[] attendees);

	String[] getAttendees();

	void init();
}
