using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Globalization;
using System.ComponentModel.DataAnnotations;

namespace Year_4_Project.Models
{
    public class Event
    {
        [Key]
        public int eventID { get; set; }

        public String eventName { get; set; }

        public String eventVenue { get; set; }

        public String eventDate { get; set; }

    }
}