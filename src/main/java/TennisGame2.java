import java.util.Optional;
import java.util.Set;

public class TennisGame2 implements TennisGame
{
    private Set<Scoring> scoringNamings = Set.of(new AllScoring(), new DeuceScoring(), new DefaultScoring(), new AdvantageScoring(), new WinScoring());

    public int p1Points = 0;
    public int p2Points = 0;
    
    public String P1res = "";
    public String P2res = "";
    private String player1Name;
    private String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        for (Scoring scoring : scoringNamings) {
            Optional<String> result = scoring.calculateScoring(p1Points, p2Points);
            if (result.isPresent()) {
                return result.get();
            }
        }
        throw new Error();
    }

    private String singleScoreName(int points) {
        return switch (points) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> throw new IllegalArgumentException(points + " has no canonic name");
        };
    }

    interface Scoring {
        Optional<String> calculateScoring(int p1Score, int p2Score);
    }

    class AllScoring implements Scoring {
        @Override
        public Optional<String> calculateScoring(int p1Score, int p2Score) {
            if (p1Score != p2Score) return Optional.empty();
            if (p1Score > 2) return Optional.empty();
            return Optional.of(singleScoreName(p1Score) + "-All");
        }
    }

    class DeuceScoring implements Scoring {
        @Override
        public Optional<String> calculateScoring(int p1Score, int p2Score) {
            if (p1Score != p2Score) return Optional.empty();
            if (p1Score < 3) return Optional.empty();
            return Optional.of("Deuce");
        }
    }

    class DefaultScoring implements Scoring {

        @Override
        public Optional<String> calculateScoring(int p1Score, int p2Score) {
            if (p1Score == p2Score) return Optional.empty();
            if (Math.max(p1Score, p2Score) >= 4) return Optional.empty();
            return Optional.of(singleScoreName(p1Score) + "-" + singleScoreName(p2Score));
        }
        
    }

    class AdvantageScoring implements Scoring {

        @Override
        public Optional<String> calculateScoring(int p1Score, int p2Score) {
            if (Math.min(p1Score, p2Score) < 3) return Optional.empty();
            if (Math.abs(p1Score - p2Score) > 1) return Optional.empty();
            if (p1Score > p2Score) {
                return Optional.of("Advantage player1");
            } else if (p2Score > p1Score) {
                return Optional.of("Advantage player2");
            } else {
                return Optional.empty();
            }
        }
        
    }

    class WinScoring implements Scoring {

        @Override
        public Optional<String> calculateScoring(int p1Score, int p2Score) {
            if (Math.max(p1Score, p2Score) < 4) return Optional.empty();
            if (Math.abs(p1Score - p2Score) < 2) return Optional.empty();
            if (p1Score > p2Score) {
                return Optional.of("Win for player1");
            } else if (p2Score > p1Score) {
                return Optional.of("Win for player2");
            } else {
                return Optional.empty();
            }
        }
        
    }
    
    public void SetP1Score(int number){
        
        for (int i = 0; i < number; i++)
        {
            P1Score();
        }
            
    }
    
    public void SetP2Score(int number){
        
        for (int i = 0; i < number; i++)
        {
            P2Score();
        }
            
    }
    
    public void P1Score(){
        p1Points++;
    }
    
    public void P2Score(){
        p2Points++;
    }

    public void wonPoint(String player) {
        if (player == "player1")
            P1Score();
        else
            P2Score();
    }
}