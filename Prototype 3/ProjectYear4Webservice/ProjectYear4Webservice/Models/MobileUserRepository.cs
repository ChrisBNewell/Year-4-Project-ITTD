using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Year_4_Project.Models;

namespace ProjectYear4Webservice.Models
{
    public class MobileUserRepository : IMobileUserRepository
    {
        private ApplicationDbContext db = new ApplicationDbContext();
        private List<MobileUser> users = new List<MobileUser>();
        
        private int _nextid = 1;

        public MobileUserRepository()
        {
            users = db.MobileUsers.ToList();
        }

        public IEnumerable<MobileUserDTO> GetAll()
        {
            var userDTO = from u in db.MobileUsers
                          select new MobileUserDTO()
                             {
                                 MobileUserID = u.MobileUserID,
                                 Username =u.Username,
                                 Password = u.Password
                             };

            return userDTO;
            //return users;
        }

        public MobileUser Get(int id)
        {
            return users.Find(u => u.MobileUserID == id);
        }

        public bool Update(int id, MobileUser mu)
        {
            if (mu == null)
            {
                throw new ArgumentException("Error: Update event, event is Null");
            }
            int index = users.FindIndex(u => u.MobileUserID == id);
            if(index  == -1)
            {
                return false;
            }

           mu.MobileUserID = id;
           MobileUser toReplace = db.MobileUsers.Find(id);

           db.MobileUsers.Remove(toReplace);
           db.MobileUsers.Add(mu);

           db.SaveChanges();

           users = db.MobileUsers.ToList();

           return true;
        }

        public MobileUser Add(MobileUser mu)
        {
            if (users == null)
            {
                throw new ArgumentException("Error: Cannot add a null value to Mobile User!");
            }
            mu.MobileUserID = _nextid++;
            db.MobileUsers.Add(mu);
            db.SaveChanges();
            users = db.MobileUsers.ToList();
            return mu;
        }

        public void Remove(int id)
        {
            MobileUser mu = db.MobileUsers.Find(id);
            db.MobileUsers.Remove(mu);
            db.SaveChanges();

            users = db.MobileUsers.ToList();
        }
    }
}