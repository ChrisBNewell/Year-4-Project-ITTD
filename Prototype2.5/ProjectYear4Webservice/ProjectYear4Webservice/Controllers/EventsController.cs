using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Year_4_Project.Models;

namespace Year_4_Project.Controllers
{
    public class EventsController : ApiController
    {

        static readonly IEventRepository repository = new EventRepository();

        // GET api/values
        public IEnumerable<Event> GetAllEvents()
        {
            return repository.GetAll();
        }

        // GET api/values/5
        public Event GetEvent(int id)
        {
            Event ev = repository.Get(id);
            if (ev == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            return ev;
        }

        // POST api/values
        public HttpResponseMessage PostEvent(Event ev)
        {
            ev = repository.Add(ev);
            var response = Request.CreateResponse<Event>(HttpStatusCode.Created, ev);

            string uri = Url.Link("DefaultApi", new { id = ev.eventID });
            response.Headers.Location = new Uri(uri);
            return response;
        }

        // POST api/values


        // PUT api/values/5
        public void PutEvent(int id, Event ev)
        {
            //repository.Update(ev);
            if (!repository.Update(id, ev))
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
        }

        // DELETE api/values/5
        public void Delete(int id)
        {
            Event ev = repository.Get(id);
            if (ev == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            repository.Remove(id);
        }
    }
}