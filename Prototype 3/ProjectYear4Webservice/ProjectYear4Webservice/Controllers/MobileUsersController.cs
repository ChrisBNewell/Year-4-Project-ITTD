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
    public class MobileUsersController : ApiController
    {
        static readonly IMobileUserRepository repository = new MobileUserRepository();

        // GET api/values
        public IEnumerable<MobileUserDTO> GetAllMobileUsers()
        {
            return repository.GetAll();
        }

        // GET api/values/5
        public MobileUser GetMobileUser(int id)
        {
            MobileUser mu = repository.Get(id);
            if (mu == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            return mu;
        }

         // PUT api/values/5
        public void PutMobileUser(int id, MobileUser mu)
        {
            if (!repository.Update(id, mu))
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
        }

        //// PUT: api/MobileUsers/5
        //[ResponseType(typeof(void))]
        //public IHttpActionResult PutMobileUser(int id, MobileUser mobileUser)
        //{
        //    if (!ModelState.IsValid)
        //    {
        //        return BadRequest(ModelState);
        //    }

        //    if (id != mobileUser.userID)
        //    {
        //        return BadRequest();
        //    }

        //    db.Entry(mobileUser).State = EntityState.Modified;

        //    try
        //    {
        //        db.SaveChanges();
        //    }
        //    catch (DbUpdateConcurrencyException)
        //    {
        //        if (!MobileUserExists(id))
        //        {
        //            return NotFound();
        //        }
        //        else
        //        {
        //            throw;
        //        }
        //    }

        //    return StatusCode(HttpStatusCode.NoContent);
        //}

         // POST api/values
        public HttpResponseMessage PostMobileUser(MobileUser mu)
        {
            mu = repository.Add(mu);
            var response = Request.CreateResponse<MobileUser>(HttpStatusCode.Created, mu);

            string uri = Url.Link("DefaultApi", new { id = mu.MobileUserID });
            response.Headers.Location = new Uri(uri);
            return response;
        }

        // DELETE api/values/5
        public void DeleteMobileUser(int id)
        {
            MobileUser mu = repository.Get(id);
            if (mu == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            repository.Remove(id);
        }
    }
}