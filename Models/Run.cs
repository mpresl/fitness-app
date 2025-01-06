namespace FitnessApp.Models
{
    public class Run
    {
        public int Id { get; set; }

        [Required]
        public double Distance { get; set; }

        [Required]
        public double Time { get; set; }
    }
}
