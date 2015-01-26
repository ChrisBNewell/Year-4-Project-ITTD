using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using Year_4_Project.Models;

namespace ProjectYear4Webservice.Models
{
    public class MobileUser
    {
        [Key]
        public int MobileUserID { get; set; }

        public string Username { get; set; }

        public string Password { get; set; }

        public virtual ICollection<Reservation> Reservations { get; set; }
         
    }
}