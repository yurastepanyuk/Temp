package day12.skating;

public interface SkatingRink {

    public Skates getSkates(Skater skater);
    public void returnSkates(Skates skates, Skater skater);

}
