using System.ComponentModel.DataAnnotations;

namespace FitnessApp.Models
{
    public class Activity
    {
        public int Id { get; set; }

        [Required]
        public double Distance { get; set; }

        [Required]
        public double Time { get; set; }
    }
}
