package com.cs616.studybuddy_mockup;

import hirondelle.date4j.DateTime;

/**
 * Created by Alex on 11/1/2015.
 */
public class Event {

        public Event(DateTime dat, String titl){
                title=titl;
                eventDate =dat;
        }

        public Event(){

        }
        public String title;

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public DateTime getEventDate() {
                return eventDate;
        }

        public void setEventDate(DateTime eventDate) {
                this.eventDate = eventDate;
        }

        public DateTime eventDate;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public long id;

}
