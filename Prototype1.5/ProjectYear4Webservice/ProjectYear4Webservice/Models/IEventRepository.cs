using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Year_4_Project.Models
{
    interface IEventRepository
    {
        IEnumerable<Event> GetAll();
        Event Get(int id);
        Event Add(Event ev);
        void Remove(int id);
        bool Update(Event ev); 
    }
}
