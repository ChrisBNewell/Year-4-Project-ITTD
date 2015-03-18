using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using ProjectYear4Webservice.Models;

namespace ProjectYear4Webservice.Controllers
{
    public class ReservationsController : ApiController
    {
        static readonly IReservationRepository repository = new ReservationRepository();

        // GET: api/Reservations
        public IEnumerable<ReservationDTO> GetAllReservations()
        {
            return repository.GetAll();
        }

        // GET: api/Reservations/5
        public IEnumerable<ReservationDTO> GetReservation(int id)
        {
            var rs = repository.Get(id);

            if (rs == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            return rs;
        }

        // POST: api/Reservations
        public HttpResponseMessage PostReservation(Reservation rs)
        {
            rs = repository.Add(rs);
            var response = Request.CreateResponse<Reservation>(HttpStatusCode.Created, rs);

            string uri = Url.Link("DefaultApi", new { id = rs.ReservationID });
            response.Headers.Location = new Uri(uri);
            return response;
        }


        // DELETE: api/Reservations/5
        public void Delete(int id)
        {
            var rs = repository.Get(id);
            if (rs == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            repository.Remove(id);
        }
    }
}
