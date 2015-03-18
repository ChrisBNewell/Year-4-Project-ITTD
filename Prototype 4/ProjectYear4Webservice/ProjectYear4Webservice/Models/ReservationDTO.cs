using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ProjectYear4Webservice.Models
{
    public class ReservationDTO
    {
        public int AttendeeID { get; set; }
        public int EventID { get; set; }
        public string Attendee { get; set; }
        public string EventReservation { get; set; }
    }
}