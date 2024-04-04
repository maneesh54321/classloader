package com.ms.ioc;

public class MeetingService implements IMeetingService {

	private final String[] attendees = {"Alice", "Bob", "Ram"};

	@Override
	public String[] getAttendees() {
		return attendees;
	}
}
