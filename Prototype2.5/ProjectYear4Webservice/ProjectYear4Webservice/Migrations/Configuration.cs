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
            AutomaticMigrationsEnabled = false;
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

            //context.Events.AddOrUpdate(e => e.eventID,
            //     new Event
            //     {
            //         eventName = "Dinner",
            //         eventVenue = "Restaurant",
            //         eventDate = DateTime.Now.ToString()
            //     },

            //     new Event
            //     {
            //         eventName = "Social",
            //         eventVenue = "Cinema",
            //         eventDate = DateTime.Now.ToString()
            //     },
            //     new Event
            //     {
            //         eventName = "Gathering",
            //         eventVenue = "Park",
            //         eventDate = DateTime.Now.ToString()
            //     }
            //);


            var events = new List<Event>
            {
                new Event{eventName = "Dinner", eventVenue = "Restaurant", eventDate = DateTime.Now.ToString()},
                new Event{eventName = "Social", eventVenue = "Cinema", eventDate = DateTime.Now.ToString()},
                new Event{eventName = "Gathering", eventVenue = "Park", eventDate = DateTime.Now.ToString()},
                new Event{eventName = "Meeting", eventVenue = "Office", eventDate = DateTime.Now.ToString()},
                new Event{eventName = "Breakfast", eventVenue = "Cafe", eventDate = DateTime.Now.ToString()},
                new Event{eventName = "Class", eventVenue = "College", eventDate = DateTime.Now.ToString()},
                new Event{eventName = "Football", eventVenue = "Pitch", eventDate = DateTime.Now.ToString()},
                new Event{eventName = "Practice", eventVenue = "Hall", eventDate = DateTime.Now.ToString()}
            };

            events.ForEach(e => context.Events.Add(e));
            context.SaveChanges();


            var mobileUsers = new List<MobileUser>
            {
                new MobileUser{ Username = "Chris", Password = "abc" },
                new MobileUser{ Username = "Charles", Password = "123" },
                new MobileUser{ Username = "Shane", Password = "xyz" }            
            };
            mobileUsers.ForEach(m => context.MobileUsers.Add(m));
            context.SaveChanges();


            var reservations = new List<Reservation>
            {
                new Reservation{ MobileUserID = 0,  EventID = 50},
                new Reservation{ MobileUserID = 0,  EventID = 83},
                new Reservation{ MobileUserID = 1,  EventID = 123},
                new Reservation{ MobileUserID = 1,  EventID = 125},
                new Reservation{ MobileUserID = 1,  EventID = 127},
                new Reservation{ MobileUserID = 1,  EventID = 132},
                new Reservation{ MobileUserID = 2,  EventID = 125}
            };
            reservations.ForEach(r => context.Reservations.Add(r));
            context.SaveChanges();
        }
    }
}
