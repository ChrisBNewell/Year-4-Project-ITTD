﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectYear4Webservice.Models
{
    interface IReservationRepository
    {
        IEnumerable<Reservation> GetAll();
        Reservation Get(int id);
        Reservation Add(Reservation rs);
        void Remove(int id);
    }
}
