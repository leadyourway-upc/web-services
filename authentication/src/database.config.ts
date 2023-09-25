import { TypeOrmModuleOptions } from '@nestjs/typeorm';
import { User } from './auth/infrastructure/entity/user.entity';

const databaseConfig: TypeOrmModuleOptions = {
  type: 'mysql',
  host: 'localhost',
  port: 3306,
  username: 'root',
  password: 'root',
  database: 'db_leadyourway',
  entities: [User],
  synchronize: true,
};

export default databaseConfig;
