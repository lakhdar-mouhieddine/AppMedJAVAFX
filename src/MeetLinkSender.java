public interface MeetLinkSender {
    void setSenderEmailAndPassword(String email, String password);
    void sendGoogleMeetLink(String link);
}
