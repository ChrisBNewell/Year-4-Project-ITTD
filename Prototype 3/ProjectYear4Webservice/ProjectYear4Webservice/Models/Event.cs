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
        public int EventID { get; set; }

        public String EventName { get; set; }

        public String EventVenue { get; set; }

        public String EventDate { get; set; }

        public virtual ICollection<Reservation> Reservations { get; set; }
    }
}