using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ProjectYear4Webservice.Models
{
    public class MobileUserDTO
    {
        public int MobileUserID { get; set; }

        public String Username { get; set; }

        public String Password { get; set; }

        public String UserEmail { get; set; }
    }
}