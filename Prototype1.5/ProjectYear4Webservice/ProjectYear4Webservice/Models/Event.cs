using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Globalization;

namespace Year_4_Project.Models
{
    public class Event
    {
        public int eventID { get; set; }
        public String eventName { get; set; }
        public String eventVenue { get; set; }
        public String eventDate { get; set; }
    }
}