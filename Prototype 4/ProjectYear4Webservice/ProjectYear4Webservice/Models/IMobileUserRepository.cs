using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectYear4Webservice.Models
{
    interface IMobileUserRepository
    {
        IEnumerable<MobileUserDTO> GetAll();
        MobileUserDTO Get(int id);
        MobileUserDTO GetMobileUser(string username, string password);
        bool CheckMobileUser(string username, string password);
        MobileUser Add(MobileUser mu);
        void Remove(int id);
        bool Update(int id, MobileUser mu); 
    }
}