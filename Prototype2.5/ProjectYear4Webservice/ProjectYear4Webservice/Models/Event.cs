using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Globalization;
using System.ComponentModel.DataAnnotations;
using ProjectYear4Webservice.Models;

namespace Year_4_Project.Models
{
    public class Event
    {
        [Key]
        public int eventID { get; set; }

        public String eventName { get; set; }

        public String eventVenue { get; set; }

        public String eventDate { get; set; }

        public virtual ICollection<Reservation> Reservations { get; set; }
    }
}