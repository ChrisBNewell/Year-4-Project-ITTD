using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ProjectYear4Webservice.Models
{
    public class ReservationRepository : IReservationRepository
    {
        private ApplicationDbContext db = new ApplicationDbContext();
        private List<Reservation> reservations = new List<Reservation>();
        private int _nextid = 1;

        public ReservationRepository()
        {
            reservations = db.Reservations.ToList();
        }

        public IEnumerable<Reservation> GetAll()
        {
            return reservations;
        }

        public Reservation Get(int id)
        {
            return reservations.Find(r => r.ReservationID == id);
        }

        public Reservation Add(Reservation rs)
        {
            if (reservations == null)
            {
                throw new ArgumentException("Error: Cannot add a null value to events!");
            }
            rs.ReservationID = _nextid++;
            db.Reservations.Add(rs);
            db.SaveChanges();

            reservations = db.Reservations.ToList();

            return rs;
        }

        public void Remove(int id)
        {
            Reservation rs = db.Reservations.Find(id);
            db.Reservations.Remove(rs);
            db.SaveChanges();

            reservations = db.Reservations.ToList();
        }
    }
}