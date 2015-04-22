namespace ProjectYear4Webservice.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class initial1 : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Events", "CreatorEmail", c => c.String());
            AddColumn("dbo.MobileUsers", "UserEmail", c => c.String());
        }
        
        public override void Down()
        {
            DropColumn("dbo.MobileUsers", "UserEmail");
            DropColumn("dbo.Events", "CreatorEmail");
        }
    }
}
