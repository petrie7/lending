package peter.taylor.lending;

public class NoLoanExistsException extends RuntimeException {

    private Long loanId;

    public NoLoanExistsException(Long loanId) {
        super();
        this.loanId = loanId;
    }

    @Override
    public String getMessage() {
        return "No loan exists for id: " + loanId;
    }
}
