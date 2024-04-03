package com.ms.ioc;

public class Meeting implements IMeeting{

	private String[] attendees;

	private String name;

	private final IMeetingService meetingService;

	public Meeting(IMeetingService meetingService) {
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

	@Override
	public void init() {
		this.setAttendees(this.meetingService.getAttendees());
	}
}
