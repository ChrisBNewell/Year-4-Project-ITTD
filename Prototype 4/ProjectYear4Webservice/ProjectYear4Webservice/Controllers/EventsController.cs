using ProjectYear4Webservice.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
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
            //TEST PUSH NOTIFICATION
            //await WebApiPushNotificationSender.SendNotificationAsync();
            //Console.ReadLine();
            
            return repository.GetAll();
        }

        //public async void testTask()
        //{
            
        //}

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


        //GET api/events/{creatorEmail}
        public IEnumerable<EventDTO> GetCreatorsEvents(string email)
        {
            IEnumerable<EventDTO> ev = repository.GetEventsBasedOnCreatorEmail(email);
            if (ev == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            return ev;
        }

        // POST api/events
        public IHttpActionResult PostEvent(Event ev)
        {
            if(ev == null)
            {
                return BadRequest();
            }
            ev = repository.Add(ev);
            //var response = Request.CreateResponse<Event>(HttpStatusCode.Created, ev);

            //string uri = Url.Link("DefaultApi", new { id = ev.EventID });
            //response.Headers.Location = new Uri(uri);
            return Ok(ev);
        }

        // POST api/values


        // PUT api/events/5
        public async Task PutEvent(int id, Event ev)
        {
            //repository.Update(ev);
            if (!repository.Update(id, ev))
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            String pushMessage = "Event details Edited!";
            await WebApiPushNotificationSender.SendNotificationAsync(pushMessage);
        }

            //DELETE api/events/5
        public async Task Delete(int id)
        {
            var tempEvent = repository.Get(id);
            if (tempEvent == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            
            repository.Remove(id);        
            String pushMessage = "Event deleted!";
            await WebApiPushNotificationSender.SendNotificationAsync(pushMessage);
        }

        // DELETE api/events/5
    //    public async Task Delete(int id, String username, String password)
    //    {
    //        IMobileUserRepository mob = new MobileUserRepository();
    //        if (mob.CheckMobileUser(username, password))
    //        {
    //            var tempUser = mob.GetMobileUser(username, password);
    //            var tempEvent = repository.Get(id);
    //            if (tempEvent == null)
    //            {
    //                throw new HttpResponseException(HttpStatusCode.NotFound);
    //            }

    //            if (tempUser.UserEmail == tempEvent.CreatorEmail)
    //            {
    //                repository.Remove(id);
    //            }
    //            else
    //            {
    //                throw new HttpResponseException(HttpStatusCode.BadRequest);
    //            }
    //        }
    //        else
    //        {
    //            throw new HttpResponseException(HttpStatusCode.NotFound);
    //        }
    //        String pushMessage = "Event deleted!";
    //        await WebApiPushNotificationSender.SendNotificationAsync(pushMessage);
    //    }
    }
}