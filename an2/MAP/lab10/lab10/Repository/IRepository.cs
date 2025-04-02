using lab10.Domain;
using System;
using System.Collections.Generic;
using System.Linq;

namespace lab10.Repository;

public interface IRepository<ID, E> where E : Entity<ID>
{
    E FindOne(ID id);
    IEnumerable<E> FindAll();
    E Save(E entity);
    E Delete (E entity);
    E Update(E entity);
}