using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectYear4Webservice.Models
{
    interface IMobileUserRepository
    {
        IEnumerable<MobileUser> GetAll();
        MobileUser Get(int id);
        MobileUser Add(MobileUser mu);
        void Remove(int id);
        bool Update(int id, MobileUser mu); 
    }
}