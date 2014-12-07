namespace ProjectYear4Webservice.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;
    using ProjectYear4Webservice.Models;
    using Year_4_Project.Models;

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

            context.Events.AddOrUpdate(e => e.eventID,
                 new Event
                 {
                     eventName = "Dinner",
                     eventVenue = "Restaurant",
                     eventDate = DateTime.Now.ToString()
                 },

                 new Event
                 {
                     eventName = "Social",
                     eventVenue = "Cinema",
                     eventDate = DateTime.Now.ToString()
                 },
                 new Event
                 {
                     eventName = "Gathering",
                     eventVenue = "Park",
                     eventDate = DateTime.Now.ToString()
                 }
            );
        }
    }
}
