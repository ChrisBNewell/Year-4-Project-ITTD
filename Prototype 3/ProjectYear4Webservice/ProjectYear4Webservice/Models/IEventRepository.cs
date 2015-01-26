using ProjectYear4Webservice.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Year_4_Project.Models
{
    interface IEventRepository
    {
        IEnumerable<EventDTO> GetAll();
        EventDTO Get(int id);
        Event Add(Event ev);
        void Remove(int id);
        bool Update(int id, Event ev); 
    }
}
