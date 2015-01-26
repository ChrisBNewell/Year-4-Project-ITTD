using ProjectYear4Webservice.Models;
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

        // GET api/events
        public IEnumerable<EventDTO> GetAllEvents()
        {
            return repository.GetAll();
        }

        // GET api/events/5
        public EventDTO GetEvent(int id)
        {
            EventDTO ev = repository.Get(id);
            if (ev == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            return ev;
        }

        // POST api/events
        public HttpResponseMessage PostEvent(Event ev)
        {
            ev = repository.Add(ev);
            var response = Request.CreateResponse<Event>(HttpStatusCode.Created, ev);

            string uri = Url.Link("DefaultApi", new { id = ev.EventID });
            response.Headers.Location = new Uri(uri);
            return response;
        }

        // POST api/values


        // PUT api/events/5
        public void PutEvent(int id, Event ev)
        {
            //repository.Update(ev);
            if (!repository.Update(id, ev))
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
        }

        // DELETE api/events/5
        public void Delete(int id)
        {
            EventDTO ev = repository.Get(id);
            if (ev == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            repository.Remove(id);
        }
    }
}