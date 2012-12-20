INSERT INTO action_parameter_type (action_parameter_type_id,action_parameter_type_name,action_parameter_type_class) VALUES (1,"Integer","java.lang.Integer");
INSERT INTO action_parameter_type (action_parameter_type_id,action_parameter_type_name,action_parameter_type_class) VALUES (2,"String","java.lang.String");
INSERT INTO action_parameter_type (action_parameter_type_id,action_parameter_type_name,action_parameter_type_class) VALUES (3,"Long","java.lang.Long");
INSERT INTO action_parameter_type (action_parameter_type_id,action_parameter_type_name,action_parameter_type_class) VALUES (4,"Double","java.lang.Double");
INSERT INTO action_parameter_type (action_parameter_type_id,action_parameter_type_name,action_parameter_type_class) VALUES (5,"Float","java.lang.Float");
INSERT INTO action_parameter_type (action_parameter_type_id,action_parameter_type_name,action_parameter_type_class) VALUES (6,"Boolean","java.lang.Boolean");

INSERT INTO `action` (action_id,action_name,method_path) VALUES (1,'View Share','action.ViewShareEndPoint');
INSERT INTO action_parameter (action_parameter_id,action_parameter_name,action_parameter_type_id,action_id) VALUES (1,"userId",1,1);
INSERT INTO action_parameter (action_parameter_id,action_parameter_name,action_parameter_type_id,action_id) VALUES (2,"shareId",1,1);

INSERT INTO `action` (action_id,action_name,method_path) VALUES (2,'Luppe Share','action.LuppeShareEndPoint');
INSERT INTO action_parameter (action_parameter_id,action_parameter_name,action_parameter_type_id,action_id) VALUES (3,"userId",1,2);
INSERT INTO action_parameter (action_parameter_id,action_parameter_name,action_parameter_type_id,action_id) VALUES (4,"shareId",1,2);

INSERT INTO `action` (action_id,action_name,method_path) VALUES (3,'Dig Share','action.DigShareEndPoint');
INSERT INTO action_parameter (action_parameter_id,action_parameter_name,action_parameter_type_id,action_id) VALUES (5,"userId",1,3);
INSERT INTO action_parameter (action_parameter_id,action_parameter_name,action_parameter_type_id,action_id) VALUES (6,"shareId",1,3);
