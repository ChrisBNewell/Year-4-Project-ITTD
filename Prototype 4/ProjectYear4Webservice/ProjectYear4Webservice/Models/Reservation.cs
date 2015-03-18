using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Year_4_Project.Models;

namespace ProjectYear4Webservice.Models
{
    public class Reservation
    {
        public int ReservationID { get; set; }
        public int MobileUserID { get; set; }
        public int EventID { get; set; }

        public virtual MobileUser MobileUser { get; set; }
        public virtual Event Event{ get; set; }
    }
}