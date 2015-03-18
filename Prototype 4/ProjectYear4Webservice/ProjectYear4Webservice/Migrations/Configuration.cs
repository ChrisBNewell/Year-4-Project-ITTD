namespace ProjectYear4Webservice.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;
    using ProjectYear4Webservice.Models;
    using Year_4_Project.Models;
    using System.Collections.Generic;

    internal sealed class Configuration : DbMigrationsConfiguration<ProjectYear4Webservice.Models.ApplicationDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
            AutomaticMigrationDataLossAllowed = true;
        }

        protected override void Seed(ProjectYear4Webservice.Models.ApplicationDbContext context)
        {
            //  This method will be called after migrating to the latest version.

            //  You can use the DbSet<T>.AddOrUpdate() helper extension method 
            //  to avoid creating duplicate seed data. E.g.
            //
            //    context.People.AddOrUpdate(
            //      p => p.FullName,
            //      new Person { FullName = "Andrew Peters" },
            //      new Person { FullName = "Brice Lambson" },
            //      new Person { FullName = "Rowan Miller" }
            //    );
            //

            //context.Events.AddOrUpdate(e => e.EventID,
            //     new Event
            //     {
            //         EventName = "Dinner",
            //         EventVenue = "Restaurant",
            //         EventDate = DateTime.Now.ToString()
            //     },

            //     new Event
            //     {
            //         EventName = "Social",
            //         EventVenue = "Cinema",
            //         EventDate = DateTime.Now.ToString()
            //     },
            //     new Event
            //     {
            //         EventName = "Gathering",
            //         EventVenue = "Park",
            //         EventDate = DateTime.Now.ToString()
            //     }
            //);
            List<Reservation> removeReservations = new List<Reservation>();
            removeReservations = context.Reservations.ToList();
            foreach (Reservation r in removeReservations)
            {
                context.Reservations.Remove(r);
            }
            List<Event> removeEvents = new List<Event>();
            removeEvents = context.Events.ToList();
            foreach(Event e in removeEvents)
            {
                context.Events.Remove(e);
            }
            List<MobileUser> removeMobileUsers = new List<MobileUser>();
            removeMobileUsers = context.MobileUsers.ToList();
            foreach (MobileUser m in removeMobileUsers)
            {
                context.MobileUsers.Remove(m);
            }


            var events = new List<Event>
            {
                new Event{ EventName = "Dinner", EventVenue = "Restaurant", EventDate = DateTime.Now.ToString(), CreatorEmail = "chris@gmail.com" },
                new Event{ EventName = "Social", EventVenue = "Cinema", EventDate = DateTime.Now.ToString(), CreatorEmail = "charles@gmail.com" },
                new Event{ EventName = "Gathering", EventVenue = "Park", EventDate = DateTime.Now.ToString(), CreatorEmail = "shane@gmail.com" },
                new Event{ EventName = "Meeting", EventVenue = "Office", EventDate = DateTime.Now.ToString(), CreatorEmail = "chris@gmail.com" },
                new Event{ EventName = "Breakfast", EventVenue = "Cafe", EventDate = DateTime.Now.ToString(), CreatorEmail = "chris@gmail.com" },
                new Event{ EventName = "Class", EventVenue = "College", EventDate = DateTime.Now.ToString(), CreatorEmail = "charles@gmail.com" },
                new Event{ EventName = "Football", EventVenue = "Pitch", EventDate = DateTime.Now.ToString(), CreatorEmail = "chris@gmail.com" },
                new Event{ EventName = "Practice", EventVenue = "Hall", EventDate = DateTime.Now.ToString(), CreatorEmail = "charles@gmail.com" }
            };
            events.ForEach(e => context.Events.AddOrUpdate(e));
            //events.ForEach(e => context.Events.AddOrUpdate(p => p.EventName, e));
            context.SaveChanges();

            var mobileUsers = new List<MobileUser>
            {
                new MobileUser{ Username = "Chris", Password = "abc", UserEmail = "chris@gmail.com" },
                new MobileUser{ Username = "Charles", Password = "123", UserEmail = "charles@gmail.com" },
                new MobileUser{ Username = "Shane", Password = "xyz", UserEmail = "shane@gmail.com" }            
            };
            mobileUsers.ForEach(m => context.MobileUsers.AddOrUpdate(m));
            //mobileUsers.ForEach(m => context.MobileUsers.AddOrUpdate(p => p.Username, m));
            context.SaveChanges();
          
            var reservations = new List<Reservation>
            {
                //new Reservation{ MobileUser = mobileUsers.Single( i => i.MobileUserID == 0) },
                //new Reservation{ MobileUserID = 1,  EventID = 50},
                //new Reservation{ MobileUserID = 2,  EventID = 83},
                //new Reservation{ MobileUserID = 3,  EventID = 123},
                //new Reservation{ MobileUserID = 3,  EventID = 125},
                //new Reservation{ MobileUserID = 2,  EventID = 127},
                //new Reservation{ MobileUserID = 1,  EventID = 128},
                //new Reservation{ MobileUserID = 2,  EventID = 129},
                //new Reservation{ MobileUserID = 2,  EventID = 130}
                             
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Charles").MobileUserID, EventID = events.Single(i => i.EventName == "Meeting").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Charles").MobileUserID, EventID = events.Single(i => i.EventName == "Football").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Charles").MobileUserID, EventID = events.Single(i => i.EventName == "Breakfast").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Charles").MobileUserID, EventID = events.Single(i => i.EventName == "Practice").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Charles").MobileUserID, EventID = events.Single(i => i.EventName == "Dinner").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Charles").MobileUserID, EventID = events.Single(i => i.EventName == "Social").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Charles").MobileUserID, EventID = events.Single(i => i.EventName == "Class").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Chris").MobileUserID, EventID = events.Single(i => i.EventName == "Dinner").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Chris").MobileUserID, EventID = events.Single(i => i.EventName == "Meeting").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Chris").MobileUserID, EventID = events.Single(i => i.EventName == "Practice").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Chris").MobileUserID, EventID = events.Single(i => i.EventName == "Football").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Chris").MobileUserID, EventID = events.Single(i => i.EventName == "Breakfast").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Chris").MobileUserID, EventID = events.Single(i => i.EventName == "Social").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Shane").MobileUserID, EventID = events.Single(i => i.EventName == "Class").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Shane").MobileUserID, EventID = events.Single(i => i.EventName == "Breakfast").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Shane").MobileUserID, EventID = events.Single(i => i.EventName == "Gathering").EventID },
                new Reservation { MobileUserID = mobileUsers.Single(i => i.Username == "Shane").MobileUserID, EventID = events.Single(i => i.EventName == "Meeting").EventID }
               
            };           
            reservations.ForEach(r => context.Reservations.AddOrUpdate(r));
            //reservations.ForEach(r => context.Reservations.AddOrUpdate(p => p.MobileUserID, r));
            context.SaveChanges();
        }
    }
}
