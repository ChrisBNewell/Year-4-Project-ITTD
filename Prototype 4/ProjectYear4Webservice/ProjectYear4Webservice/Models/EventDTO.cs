using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ProjectYear4Webservice.Models
{
    public class EventDTO
    {
        public int EventID { get; set; }

        public String EventName { get; set; }

        public String EventVenue { get; set; }

        public String EventDate { get; set; }

        public String CreatorEmail { get; set; }
    }
}