using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using System.Web.Http.Description;

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

        public IEnumerable<ReservationDTO> GetAll()
        {
            var reserveDTO = from r in db.Reservations
                        select new ReservationDTO()
                        {
                            Attendee = r.MobileUser.Username,
                            EventReservation = r.Event.EventName
                        };

            return reserveDTO;
            //return reservations;
        }

        public IEnumerable<ReservationDTO> Get(int id)
        {
            var reserveDTO = from r in db.Reservations
                             where r.MobileUser.MobileUserID == id
                             select new ReservationDTO()
                             {
                                 Attendee = r.MobileUser.Username,
                                 EventReservation = r.Event.EventName
                             };

            return reserveDTO;
            //return reservations.Find(r => r.ReservationID == id);
        }

        //[ResponseType(typeof(ReservationDTO))]
        //public async Task<IHttpActionResult> Get(int id)
        //{
        //    var book = await db.Books.Include(b => b.Author).Select(b =>
        //        new BookDetailDTO()
        //        {
        //            Id = b.Id,
        //            Title = b.Title,
        //            Year = b.Year,
        //            Price = b.Price,
        //            AuthorName = b.Author.Name,
        //            Genre = b.Genre
        //        }).SingleOrDefaultAsync(b => b.Id == id);
        //    if (book == null)
        //    {
        //        return NotFound();
        //    }

        //    return Ok(book);
        //} 
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