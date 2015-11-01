package com.cs616.studybuddy_mockup;

import hirondelle.date4j.DateTime;

/**
 * Created by Alex on 11/1/2015.
 */
public class Event {
        public Event(DateTime dat, String titl){
                title=titl;
                date=dat;
        }
        public String title;
        public DateTime date;

}
