using Microsoft.EntityFrameworkCore;
using FitnessApp.Models;

namespace FitnessApp.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public DbSet<Run> Runs { get; set; }
        public DbSet<Swim> Swims { get; set; }
        public DbSet<Bike> Bikes { get; set; }
    }
}