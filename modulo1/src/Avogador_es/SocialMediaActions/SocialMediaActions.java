package Avogador_es.SocialMediaActions;

abstract class SocialMediaAction {
	private final String author;
	
	protected SocialMediaAction(String author) {
		this.author = author;
	}
	
	public boolean sameAction(SocialMediaAction action) {
		return author.equals(action.author);
	}
}

class Like extends SocialMediaAction {
    private final int likedPostID;

    public Like(String author, int likedPostID) {
        super(author);
        this.likedPostID = likedPostID;
    }

    @Override
    public boolean sameAction(SocialMediaAction action) {
        if (!(action instanceof Like)) {
            return false;
        }

        Like likeAction = (Like) action;
        return super.sameAction(action) && likedPostID == likeAction.likedPostID;
    }
}

abstract class SocialMediaActionWithText extends SocialMediaAction {
    private final String text;

    protected SocialMediaActionWithText(String author, String text) {
        super(author);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean sameAction(SocialMediaAction action) {
        if (!(action instanceof SocialMediaActionWithText)) {
            return false;
        }

        SocialMediaActionWithText textAction = (SocialMediaActionWithText) action;
        return super.sameAction(action) && text.equals(textAction.text);
    }
}

class Comment extends SocialMediaActionWithText {
    private final int commentID;
    private final int commentedPostID;

    public Comment(String author, String text, int commentID, int commentedPostID) {
        super(author, text);
        this.commentID = commentID;
        this.commentedPostID = commentedPostID;
    }

    @Override
    public boolean sameAction(SocialMediaAction action) {
        if (!(action instanceof Comment)) {
            return false;
        }

        Comment commentAction = (Comment) action;
        return super.sameAction(action) && commentID == commentAction.commentID && commentedPostID == commentAction.commentedPostID;
    }
}

class Post extends SocialMediaActionWithText {
    private final int postID;

    public Post(String author, String text, int postID) {
        super(author, text);
        this.postID = postID;
    }

    @Override
    public boolean sameAction(SocialMediaAction action) {
        if (!(action instanceof Post)) {
            return false;
        }

        Post postAction = (Post) action;
        return super.sameAction(action) && postID == postAction.postID;
    }
}