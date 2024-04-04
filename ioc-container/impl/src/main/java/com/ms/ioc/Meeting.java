package com.ms.ioc;

public class Meeting implements IMeeting{

	private String[] attendees;

	private final String name;

	private final IMeetingService meetingService;

	public Meeting(String name, IMeetingService meetingService) {
		this.name = name;
		this.meetingService = meetingService;
	}

	@Override
	public void setAttendees(String[] attendees) {
		this.attendees = attendees;
	}

	@Override
	public String[] getAttendees() {
		return attendees;
	}

	public String getName() {
		return name;
	}

	@Override
	public void init() {
		this.setAttendees(this.meetingService.getAttendees());
	}
}
