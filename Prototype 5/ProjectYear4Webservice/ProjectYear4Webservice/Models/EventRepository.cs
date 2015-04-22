using ProjectYear4Webservice.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using System.Web.Http.Description;

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

            //events = db.Events.Include(c => c.Reservations).AsNoTracking().ToList(); 
            events = db.Events.ToList();
        }

        public IEnumerable<EventDTO> GetAll()
        {
            //var eventReservations = db.Events.Include(c => c.Reservations);
            //var records = from entity in context.Entities
            //  select new 
            //  {
            //      Prop1 = entity.Prop1,
            //      Prop2 = entity.Prop2,
            //      ChildProp = entity.Child.Prop
            //  }
            //return Json(records);
            var eventDTOs = from e in db.Events
                        select new EventDTO()
                        {
                            EventID = e.EventID,
                            EventName = e.EventName,
                            EventVenue = e.EventVenue,
                            EventDate = e.EventDate,
                            CreatorEmail = e.CreatorEmail
                        };

            return eventDTOs;
        }

        //[ResponseType(typeof(EventDTO))]
        //public async Task<IHttpActionResult> GetEvent(int id)
        //{
        //    var searchedEvent = await db.Events.Select(e =>
        //        new EventDTO()
        //        {
        //            EventID = e.EventID,
        //            EventName = e.EventName,
        //            EventVenue = e.EventVenue,
        //            EventDate = e.EventDate
        //        }).SingleOrDefaultAsync(e => e.EventID == id);
        //    if (searchedEvent == null)
        //    {
        //        throw new ArgumentException("Error: Cannot get single event of id: " + id + "!");
        //    }

        //    return searchedEvent;
        //} 

        public EventDTO Get(int id)
        {
            Event findEvent = events.Find(e => e.EventID == id);
            var eventDTO = new EventDTO();
                eventDTO.EventID = findEvent.EventID;
                eventDTO.EventName = findEvent.EventName;
                eventDTO.EventVenue = findEvent.EventVenue;
                eventDTO.EventDate = findEvent.EventDate;
                eventDTO.CreatorEmail = findEvent.CreatorEmail;

            return eventDTO;
            //return events.Find(e => e.EventID == id);
        }

        //GET api/Events?email={email}
       public IEnumerable<EventDTO> GetEventsBasedOnCreatorEmail(string email)
        {
            var eventDTOs = from e in db.Events
                            where e.CreatorEmail == email
                            select new EventDTO()
                            {
                                EventID = e.EventID,
                                EventName = e.EventName,
                                EventVenue = e.EventVenue,
                                EventDate = e.EventDate,
                                CreatorEmail = e.CreatorEmail
                            };
            return eventDTOs;
        }

        //public bool Update(int id, Event ev)
        //{
        //    if (ev == null)
        //    {
        //        throw new ArgumentException("Error: Update event, event is Null");
        //    }
        //    int index = events.FindIndex(e => e.EventID == id);
        //    if(index  == -1)
        //    {
        //        return false;
        //    }

        //    ev.EventID = id;

        //    Event toReplace = db.Events.Find(id);
           
        //    ev.CreatorEmail = toReplace.CreatorEmail;

        //    //db.Entry(ev).State = EntityState.Modified;

        //    db.Events.Remove(toReplace);
        //    db.Events.Add(ev);

        //    db.SaveChanges();

        //    events = db.Events.ToList();

        //    return true;
        //}

       public bool Update(int id, Event ev)
       {
           var editEvent = db.Events.FirstOrDefault(e => e.EventID == id);
           if (editEvent != null)
           {
               //editEvent.EventID = ev.EventID;
               editEvent.EventName = ev.EventName;
               editEvent.EventVenue = ev.EventVenue;
               editEvent.EventDate = ev.EventDate;
               //editEvent.CreatorEmail = ev.CreatorEmail;

               db.SaveChanges();
               return true;
           }
           return false;
       }

        public Event Add(Event ev)
        {
            if (events == null)
            {
                throw new ArgumentException("Error: Cannot add a null value to events!");
            }
            ev.EventID = _nextid++;
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