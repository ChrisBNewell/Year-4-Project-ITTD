using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
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
                                 Username = u.Username,
                                 Password = u.Password,
                                 UserEmail = u.UserEmail
                             };

            return userDTO;
            //return users;
        }

        
        public MobileUserDTO Get(int id)
        {
            //return users.Find(u => u.MobileUserID == id);
            MobileUser findUser = users.Find(m => m.MobileUserID == id);
            var mobileUserDTO = new MobileUserDTO();
                mobileUserDTO.MobileUserID = findUser.MobileUserID;
                mobileUserDTO.Username = findUser.Username;
                mobileUserDTO.Password = findUser.Password;
                mobileUserDTO.UserEmail = findUser.UserEmail;

            return mobileUserDTO;
        }

        public MobileUserDTO GetMobileUser(string username, string password)
        {
            //return users.Find(u => u.Username == username && u.Password == password);
            MobileUser findUser = users.Find(u => u.Username == username && u.Password == password);
            var mobileUserDTO = new MobileUserDTO();
            mobileUserDTO.MobileUserID = findUser.MobileUserID;
            mobileUserDTO.Username = findUser.Username;
            mobileUserDTO.Password = findUser.Password;
            mobileUserDTO.UserEmail = findUser.UserEmail;

            return mobileUserDTO;
        }

       public bool CheckMobileUser(string username, string password)
        {
           var mob = users.FirstOrDefault(u => u.Username == username && u.Password == password);
           if(mob != null)
           {
               return true;
           }
           else
           {
               return false;
           }
        }

        public bool Update(int id, MobileUser mu)
        {
            if (mu == null)
            {
                throw new ArgumentException("Error: Update MobileUser, MobileUser is Null");
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