using ProjectYear4Webservice.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Year_4_Project.Models
{
    public class EventRepository : IEventRepository
    {
        private ApplicationDbContext db = new ApplicationDbContext();
        private List<Event> events = new List<Event>();
        private int _nextid = 1;

        public EventRepository()
        {
            //Add(new Event{ eventName = "Dinner", eventVenue = "Restaurant", eventDate = "23-10-14" });
            //Add(new Event{ eventName = "Social", eventVenue = "Cinema", eventDate = "24-10-14" });
            //Add(new Event { eventName = "Gathering", eventVenue = "Park", eventDate = "25-10-14" });
            events = db.Events.ToList();
        }

        public IEnumerable<Event> GetAll()
        {
            return events;
        }

        public Event Get(int id)
        {
            return events.Find(e => e.eventID == id);
        }

        public bool Update(int id, Event ev)
        {
            if (ev == null)
            {
                throw new ArgumentException("Error: Update event, event is Null");
            }
            int index = events.FindIndex(e => e.eventID == id);
            if(index  == -1)
            {
                return false;
            }

           ev.eventID = id;
           Event toReplace = db.Events.Find(id);
           //events.Remove(toReplace);
           //events.Add(ev);
           //db.Entry(ev).State = EntityState.Modified;

           db.Events.Remove(toReplace);
           db.Events.Add(ev);

           db.SaveChanges();

           events = db.Events.ToList();

           return true;
        }

        public Event Add(Event ev)
        {
            if (events == null)
            {
                throw new ArgumentException("Error: Cannot add a null value to events!");
            }
            ev.eventID = _nextid++;
            //events.Add(ev);
            db.Events.Add(ev);
            db.SaveChanges();
            events = db.Events.ToList();
            return ev;
        }

        public void Remove(int id)
        {
            Event ev = db.Events.Find(id);
            db.Events.Remove(ev);
            db.SaveChanges();

            events = db.Events.ToList();
        }
    }
}