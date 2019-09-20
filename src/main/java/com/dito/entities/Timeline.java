package com.dito.entities;

import java.util.List;

public class Timeline {
	List<TimelineEvent> timeline;

	public List<TimelineEvent> getTimeline() {
		return timeline;
	}

	public void setTimeline(List<TimelineEvent> timeline) {
		this.timeline = timeline;
	}

	public Timeline(List<TimelineEvent> timeline) {
		super();
		this.timeline = timeline;
	}
}
